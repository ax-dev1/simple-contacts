(function(){
    var contactDaoHandler = function(entityType){
        this.entityType = entityType;
    }

    contactDaoHandler.prototype.create = function(data){
        return action("/contact",'Put',data);
    }

    contactDaoHandler.prototype.update = function(data){
        return action("/contact",'Post',data);
    }

    contactDaoHandler.prototype.get = function(id){
        return action("/contact",'Get',{id:id});
    }

    contactDaoHandler.prototype.list = function(){
        return action("/contact/list");
    }

    contactDaoHandler.prototype.delete = function(id){
        return action("/contact/"+id,'Delete',{});
    }

    function action(url,act,data){
        var dfd = $.Deferred();
        $.ajax({
            url:url,
            data:data,
            type:act,
            dataType:'json'
        }).done(function(data){
            dfd.resolve(data);
        }).fail(function(){
            dfd.reject();
        });
        return dfd.promise();
    }
    brite.ContactDao = contactDaoHandler;
})();
