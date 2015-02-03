var app = (function () {
    brite.viewDefaultConfig.loadTmpl = true;
    brite.viewDefaultConfig.loadCss = false;

    (function ($) {
        brite.display("ContactsList", "#ContactsList", '0');
        brite.display("GroupsList", "#GroupsList");
    })(jQuery);

    // HB render and other helpers
    Handlebars.templates = Handlebars.templates || {};
    var render = function (templateName, data) {
        var tmpl = Handlebars.templates[templateName];
        if (!tmpl) {
            tmpl = Handlebars.compile($("#" + templateName).html());
            Handlebars.templates[templateName] = tmpl;
        }
        return tmpl(data);
    };

    // Return the groups checkbox status in contact
    Handlebars.registerHelper('select', function (contactId, groupId) {
        if (!contactId) return;
        var select = contactId.split(',');
        if ($.inArray(groupId, select) >= 0) {
            return new Handlebars.SafeString(' checked="checked" ');
        }
    });

    // Filter the contact list in group
    Handlebars.registerHelper('filter_group', function (groupId, groupString, options) {
        if (groupId == '0') return options.inverse(this);
        var groups_arr = groupString.split(',');
        if ($.inArray(groupId, groups_arr) >= 0) {
            return options.inverse(this);
        }
    });

    return {
        render: function (templateName, data) {
            return render(templateName, data);
        }
    }
})();

var main = main || {};
(function () {
    // Init
    $.getJSON("/getContacts", function (contacts) {
        main.contacts = brite.registerDao(new brite.InMemoryDaoHandler("Contacts", contacts));
    });
    $.getJSON("/getGroups", function (groups) {
        main.groups = brite.registerDao(new brite.InMemoryDaoHandler("Groups", groups));
    });

})();
