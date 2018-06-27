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

