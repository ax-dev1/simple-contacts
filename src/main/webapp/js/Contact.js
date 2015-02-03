(function () {
    brite.registerView("Contact", {emptyParent: true}, {
        create: function (contactId) {
            var view = this;
            return main.groups.list().pipe(function (groups) {
                // Render new
                if (contactId == '0') {
                    return app.render("tmpl-Contact", {groups: groups});
                }
                // Render exist
                else {
                    return main.contacts.get(contactId).pipe(function (contact) {
                        view.contact = contact;
                        return app.render("tmpl-Contact", {contact: contact, groups: groups});
                    });
                }
            });
        },
        postDisplay: function () {
            //Show contact edit modal
            $('#contact-edit-modal').modal({
                backdrop: 'static',
                keyboard: false
            });
        },
        events: {
            "click; button#save-contact": function (event) {
                var view = this;
                var select_groups = [];
                view.$el.find("input.edit-contact-groups:checked").each(function (k, v) {
                    select_groups.push($(v).val());
                });
                select_groups_str = select_groups.join(",");
                // Use the shared edit view, check New/Edit
                // If exist contact
                if (view.contact !== undefined) {
                    main.contacts.update({
                        id: view.contact.id,
                        groupId: select_groups_str,
                        firstName: view.$el.find("#edit-contact-first-name").val(),
                        lastName: view.$el.find("#edit-contact-last-name").val(),
                        phone: view.$el.find("#edit-contact-phone").val(),
                        address: view.$el.find("#edit-contact-address").val(),
                        xyz: view.$el.find("#edit-contact-xyz").val()
                    }).done(function () {
                        $('#contact-edit-modal').modal('hide');
                    });
                }
                // If new contact
                else {
                    main.contacts.create({
                        groupId: select_groups_str,
                        firstName: view.$el.find("#edit-contact-first-name").val(),
                        lastName: view.$el.find("#edit-contact-last-name").val(),
                        phone: view.$el.find("#edit-contact-phone").val(),
                        address: view.$el.find("#edit-contact-address").val(),
                        xyz: view.$el.find("#edit-contact-xyz").val()
                    }).done(function () {
                        $('#contact-edit-modal').modal('hide');
                    });
                }
            }
        }
    });

})();