(function () {
    brite.registerView("ContactsList", {emptyParent: true}, {
        create: function (groupId) {
            var view = this;
            if (groupId) {
                view.groupId = groupId;
                return app.render("tmpl-ContactsList", {groupId: groupId});
            }
        },
        postDisplay: function () {
            var view = this;
            // Listen CUD for Contacts
            //brite.dao.onDataChange("Contacts", "create,update,delete", function (event) {
            //	var daoEvent = event.daoEvent;
            //    var action = {"create":"PUT","update":"POST","delete":"DELETE"}
            //
            //    	var requestData = (daoEvent.action=="delete")?{"id":daoEvent.result}:daoEvent.result;
            //    	var requestUrl = (daoEvent.action=="delete")?"/contact/"+daoEvent.result:"/contact";
            //
	         //       	$.ajax({
	         //               type : action[daoEvent.action],
	         //               headers: {
	         //                   'X-HTTP-Method-Override': action[daoEvent.action]
	         //               },
	         //               url : requestUrl,
	         //               dataType : "json",
	         //               data:requestData,
	         //               error:  function (data) { alert("failed");}
	         //           });
            //}, view.id);
            refreshList.call(view);
        },
        events: {
            "click; button.edit-contact": function (event) {
                var $row = $(event.currentTarget);
                var contactId = $row.parents("tr").attr("data-entity-id");
                brite.display("Contact", "#modal-place", contactId);
            },
            "click; button.remove-contact": function (event) {
                var $row = $(event.currentTarget);
                var contactId = $row.parents("tr").attr("data-entity-id");
                main.contacts.delete(contactId);
            },
            "click; button#edit-group": function (event) {
                var $row = $(event.currentTarget);
                var groupId = $row.attr("data-entity-id");
                brite.display("Group", "#modal-place", groupId);
            },
            "click; button#remove-group": function (event) {
                var $row = $(event.currentTarget);
                var groupId = $row.attr("data-entity-id");
                main.groups.delete(groupId);
                brite.display("ContactsList", "#ContactsList", '0');
            }
        },
        daoEvents: {
            "dataChange": refreshList
        }

    });

// Private view method: refresh the list.
    function refreshList() {
        var view = this;
        var groupId = view.groupId;
        main.contacts.list().done(function (contacts) {
            view.$el.html(app.render("tmpl-ContactsList", {contacts: contacts, groupId: groupId}));
        });
    }

})();