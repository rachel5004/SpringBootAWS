var main = {
    init:function(){
        var _this = this;
        $('#btn-save').on('click',function(){
            _this.save();
        });
    },
    save: function(){
        var data = {
        title: $('#title').val(),
        author: $('#author').val(),
        content: $('#content').val()
        };
        $.ajax({
            type:'post',
            url:'/api/v1/post',
            dataType:'json',
            contentType:'application/json; charset=utf-8',
            data:JSON.stringify(data)
        }).done(function(){
            alert('post success');
            window.location.href = '/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    }
};
main.init();