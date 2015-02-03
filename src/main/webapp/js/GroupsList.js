(function () {
    brite.registerView("GroupsList", {emptyParent: true}, {
        create: function () {
            return app.render("tmpl-GroupsList");
        },
        postDisplay: function () {
            var view = this;
            // Listen CUD for groups
            brite.dao.onDataChange("Groups", "create,update,delete", function (event) {
                var daoEvent = event.daoEvent;
                var action = {"create":"PUT","update":"POST","delete":"DELETE"}
                
                	var requestData = (daoEvent.action=="delete")?{"id":daoEvent.result}:daoEvent.result;
                	var requestUrl = (daoEvent.action=="delete")?"/group/"+daoEvent.result:"/group";
                
	                	$.ajax({
	                        type : action[daoEvent.action],
	                        headers: {
	                            'X-HTTP-Method-Override': action[daoEvent.action]
	                        },
	                        url : requestUrl,
	                        dataType : "json",
	                        data:requestData,
	                        error:  function (data) { alert("failed");}
	                    });
            }, view.id);
            refreshGroupList.call(view);
        },
        events: {
            "click; button#add-contact": function (event) {
                brite.display("Contact", "#modal-place", '0');
            },
            "click; button#add-group": function (event) {
                brite.display("Group", "#modal-place", '0');
            },
            "click; a[role=tab]": function (event) {
                var $row = $(event.currentTarget);
                var contactId = $row.attr("data-entity-id");
                brite.display("ContactsList", "#ContactsList", contactId);
            }
        },
        daoEvents: {
            "dataChange": refreshGroupList
        }
    });
// Private view method: refresh the list.
    function refreshGroupList() {
        var view = this;
        main.groups.list().done(function (groups) {
            view.$el.html(app.render("tmpl-GroupsList", {groups: groups}));
        });
    }
})();