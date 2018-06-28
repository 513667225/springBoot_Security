/**
 * @Desc Utils
 * @Remark 此文件中的部分方法依赖 Vue JQuery Axios
 */

// 判断对象
Vue.prototype.$isObject = $isObject = function(val){
  return Object.prototype.toString.call(val).slice(8,-1).toLocaleLowerCase() === 'object';
};

/**
 * @Desc 发送Http请求
 * @Param method 请求方式 required
 * @Param path 请求接口 required
 * @Param data 请求参数 not required
 */
Vue.prototype.$http = $http = function(method,path,data){
  var service = axios.create({
    timeout: 80000,
    method: method
  });
  const timeoutmsg = 'request timeout';
  const serverwrongmsg = 'The server has gone wrong';
  /***
   * request interceptor
   */
  service.interceptors.request.use(function(config){
    const fd = new FormData();
    if($isObject(config.data)){
      for(var i in config.data)fd.append(i,config.data[i]);
    }
    config.data = fd;
    return config;
  },function(error){
    console.error(error);
    Promise.reject(error);
  });

  /***
   * respone interceptor
   */
  service.interceptors.response.use(function(response){
    const data = response.data;
    if(data.code === 200){
      return data;
    }else{
        layer.msg(serverwrongmsg);
        return Promise.reject(data.msg);
    }
  },function(error){
    console.error(error);
    if(/timeout/.test(error)){
        layer.msg(timeoutmsg);
      return Promise.reject(timeoutmsg);
    }else{
      if(String(error).length >= 15)error = serverwrongmsg;
        layer.msg(serverwrongmsg);
      return Promise.reject(error);
    }
  });

  return service(path,{data});
};


/**
 *
 * Base64 encode / decode
 *
 * @author haitao.tu
 * @date 2010-04-26
 * @email tuhaitao@foxmail.com
 *
 */
Vue.prototype.$Base64 = $Base64 = function() {
    // private property
    _keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
    // public method for encoding
    this.encode = function (input) {
        var output = "";
        var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
        var i = 0;
        input = _utf8_encode(input);
        while (i < input.length) {
            chr1 = input.charCodeAt(i++);
            chr2 = input.charCodeAt(i++);
            chr3 = input.charCodeAt(i++);
            enc1 = chr1 >> 2;
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
            enc4 = chr3 & 63;
            if (isNaN(chr2)) {
                enc3 = enc4 = 64;
            } else if (isNaN(chr3)) {
                enc4 = 64;
            }
            output = output +
                _keyStr.charAt(enc1) + _keyStr.charAt(enc2) +
                _keyStr.charAt(enc3) + _keyStr.charAt(enc4);
        }
        return output;
    }
    // public method for decoding
    this.decode = function (input) {
        var output = "";
        var chr1, chr2, chr3;
        var enc1, enc2, enc3, enc4;
        var i = 0;
        input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
        while (i < input.length) {
            enc1 = _keyStr.indexOf(input.charAt(i++));
            enc2 = _keyStr.indexOf(input.charAt(i++));
            enc3 = _keyStr.indexOf(input.charAt(i++));
            enc4 = _keyStr.indexOf(input.charAt(i++));
            chr1 = (enc1 << 2) | (enc2 >> 4);
            chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
            chr3 = ((enc3 & 3) << 6) | enc4;
            output = output + String.fromCharCode(chr1);
            if (enc3 != 64) {
                output = output + String.fromCharCode(chr2);
            }
            if (enc4 != 64) {
                output = output + String.fromCharCode(chr3);
            }
        }
        output = _utf8_decode(output);
        return output;
    }
    // private method for UTF-8 encoding
    _utf8_encode = function (string) {
        string = string.replace(/\r\n/g,"\n");
        var utftext = "";
        for (var n = 0; n < string.length; n++) {
            var c = string.charCodeAt(n);
            if (c < 128) {
                utftext += String.fromCharCode(c);
            } else if((c > 127) && (c < 2048)) {
                utftext += String.fromCharCode((c >> 6) | 192);
                utftext += String.fromCharCode((c & 63) | 128);
            } else {
                utftext += String.fromCharCode((c >> 12) | 224);
                utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                utftext += String.fromCharCode((c & 63) | 128);
            }
        }
        return utftext;
    }
    // private method for UTF-8 decoding
    _utf8_decode = function (utftext) {
        var string = "";
        var i = 0;
        var c = c1 = c2 = 0;
        while ( i < utftext.length ) {
            c = utftext.charCodeAt(i);
            if (c < 128) {
                string += String.fromCharCode(c);
                i++;
            } else if((c > 191) && (c < 224)) {
                c2 = utftext.charCodeAt(i+1);
                string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                i += 2;
            } else {
                c2 = utftext.charCodeAt(i+1);
                c3 = utftext.charCodeAt(i+2);
                string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
                i += 3;
            }
        }
        return string;
    }
};


/**
 * 文件上传
 */
(function ($) {
    function _$(id) {
        return $('#' + id);
    }
    var $FORM = '<form id="#id" action="#action" method="post" target="#target" enctype="multipart/form-data">#file</form>';
    var $FILE = '<input id="#id" type="file" name="#filename"/>'
    var $FRAME = '<iframe id="#id" name="#target"></iframe>';
    var $UPLOAD_TEMPLATE = '<div id="#templateid" style="display: none !important;">#content</div>';
    $.uploadFile = function (options) {
        if (options === null || typeof options === 'undefined') {
            console.error('参数错误(null)');
            return;
        }
        var _options = {
            filename: 'file'
        };
        var $formElement = Utils.random(16);
        var $fileElement = Utils.random(16);
        var $frameElement = Utils.random(16);
        var $templateElement = Utils.random(16);
        for (var key in options) {
            _options[key] = options[key];
        }
        var $content = $FORM.replace('#id', $formElement).replace('#target', $frameElement).replace('#action', _options['action'])
            .replace('#file', $FILE.replace('#id', $fileElement).replace('#filename', _options['filename']))
            .concat($FRAME.replace('#id', $frameElement).replace('#target', $frameElement));
        var $uploadContent = $UPLOAD_TEMPLATE.replace('#templateid', $templateElement).replace('#content', $content);
        $('body').append($uploadContent);
        _$($fileElement).click().change(function () {
            var isContinue = true;
            if (_options['changeCallback']) {
                isContinue = _options['changeCallback']($(this).val());
            }
            if (isContinue === false) {
                _$($templateElement).remove();
            } else {
                _$($formElement).submit();
            }
        });
        _$($frameElement).on('load', function () {
            if (_options['uploadCallback']) {
                _options['uploadCallback']($.parseJSON(_$($frameElement).contents().text()));
            }
            _$($templateElement).remove();
        });
    }
})(jQuery);

/**
 * 获取表单所有的值 并转换成map
 * @Return {{}}
 */
$.fn.getFormValues = function () {
    var elements = $(this).serializeArray();
    var result = {};
    $.each(elements, function (index, obj) {
        result[obj['name']] = obj['value'];
    });
    return result;
}


/**
 * 设置表单的值
 * @Param json
 * @Param options 特殊参数设置
 */
$.fn.setFormValues = function (json, options) {
    var _form = this;
    $.each(json, function (key, val) {
        var _call = options[key];
        if (isNotNull(_call)) {
            _call(key, val);
            return;
        }
        _form[key] = val;
    })
}

