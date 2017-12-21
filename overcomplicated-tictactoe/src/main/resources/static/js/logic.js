
var app = app || {};
var game = game || {};

app.logic = {

    addClickListener: function () {
        $("#board").on("click", ".square", function(ev) {
            ev.stopPropagation();
            var cellNumber = $(this).attr("id").replace("cell-", "");
            app.dataHandler.changeBoard(cellNumber);
        });
    },

    handleChangeBoardSuccess: function (response) {
        console.log("HELLO");
    },

    handleChangeBoardError: function () {

    }

};