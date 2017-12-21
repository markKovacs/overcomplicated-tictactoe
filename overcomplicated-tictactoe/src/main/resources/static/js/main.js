
var app = app || {};

app.init = function() {
    app.logic.addClickListener();
    app.logic.generateTurnMessage();
};

$(document).ready(app.init());
