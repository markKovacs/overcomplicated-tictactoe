
var app = app || {};

app.init = function() {
    app.logic.addClickListener();
};

$(document).ready(app.init());
