
var app = app || {};

app.dataHandler = {

    changeBoard: function (cellNumber) {
        $.ajax({
            url: '/api/change-board',
            method: 'POST',
            dataType: 'json',
            data: {
                cellNumber: cellNumber
            },
            success: function(response) {
                app.logic.handleChangeBoardSuccess(response);
            },
            error: function() {
                app.logic.handleChangeBoardError();
            }
        });
    }

};