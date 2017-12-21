
var app = app || {};

app.dataHandler = {

    templateAjax: function (testData) {

        $.ajax({
            url: '/api/test',
            method: 'POST',
            dataType: 'json',
            data: {
                test_data: testData
            },
            success: function(response) {
                app.logic.testFunction();
            },
            error: function() {
                app.logic.testFunction();
            }
        });

    },

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