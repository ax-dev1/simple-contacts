(function () {
    brite.registerView("Group", {emptyParent: true}, {
        create: function (groupId) {
            var view = this;
            if (groupId == '0') {
                return app.render("tmpl-Group");
            }
            else
            {
                return main.groups.get(groupId).pipe(function (group) {
                    view.group = group;
                    return app.render("tmpl-Group", {group: group});
                });
            }
        },
        postDisplay: function () {
            //Show contact edit modal
            $('#group-edit-modal').modal({
                backdrop: 'static',
                keyboard: false
            });
        },
        events: {
            "click; button#save-group": function (event) {
                var view = this;
                if (view.group !== undefined) {
                    main.groups.update({
                        id: view.group.id,
                        title: view.$el.find("#edit-group-title").val()
                    }).done(function () {
                        $('#group-edit-modal').modal('hide')
                    });
                }
                else {
                    main.groups.create({
                        title: view.$el.find("#edit-group-title").val()
                    }).done(function () {
                        $('#group-edit-modal').modal('hide')
                    });
                }
            }

        }
    });
})();