(function(){
    var groupDaoHandler = function(entity){
        this.entityType = entity;
    }

    groupDaoHandler.prototype.create = function(data){
        return action("/group",'Put',data);
    }

    groupDaoHandler.prototype.update = function(data){
        return action("/group",'Post',data);
    }

    groupDaoHandler.prototype.get = function(id){
        return action("/group",'Get',{id:id});
    }

    groupDaoHandler.prototype.list = function(){
        return action("/group/list",'Get',{});
    }

    groupDaoHandler.prototype.delete = function(id){
        return action("/group/"+id,'Delete',{id:id});
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

    brite.GroupDao = groupDaoHandler;
})();
