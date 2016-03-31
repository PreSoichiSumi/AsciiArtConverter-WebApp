/**
 * Created by s-sumi on 2016/04/01.
 */
var endpoint='/';
$(function() {
    $("#submit").on('click', function(evt) {
        var form = $("#settingForm").get()[0];
        var formData = new FormData( form );

        // Ajaxで送信
        $.ajax({
            url: endpoint,
            method: 'post',
            dataType: 'json',
            // dataに FormDataを指定
            data: formData,
            // Ajaxがdataを整形しない指定
            processData: false,
            // contentTypeもfalseに指定
            contentType: false
        }).done(function( data ) {
            console.log( 'SUCCESS', data );
            editor.setValue(data.aa);
        }).fail(function( jqXHR, textStatus, errorThrown ) {
            console.log( 'ERROR', jqXHR, textStatus, errorThrown );
        });

        return false;
    });
});