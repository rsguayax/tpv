//            $(document).bind('drop dragover', function(e) { // bloquear evento drop en todo el documento
////                $('#drop-zone').draggable('enable'); // habilitar el evento drop solo en el div drop-zone
//                e.preventDefault();
//                
//            });

var template = '<div class="preview">' +
        '<span class="imageHolder">' +
        '<img/>' +
        '<span class="uploaded"></span>' +
        '</span>' +
        '<div class="progressHolder">' +
        '<div class="progress" ></div>' +
        '</div>' +
        '</div>';

function createImage(file) {
     file.name = "asgas";
//    alert("archivo: "+file.name);
//    alert(jQuery('#file')[0].files[0].name);
//    alert(jQuery('#file').name);
    $('.preview').remove();
    var dropbox = $('#drop-zone'),
            message = $('.message', dropbox);

    $('#drop-zone').text();
//                alert(file.name);
    var preview = $(template),
            image = $('img', preview);

    var reader = new FileReader();

    image.width = 100;
    image.height = 100;

    reader.onload = function(e) {

        // e.target.result holds the DataURL which
        // can be used as a source of the image:

        image.attr('src', e.target.result);
    };

    // Reading the file as a DataURL. When finished,
    // this will trigger the onload function above:
    reader.readAsDataURL(file);

    message.hide();

    preview.appendTo(dropbox);

                $.data(file).addClass('done');
    // Associating a preview container
    // with the file, using jQuery's $.data():

                $.data(file, preview);
}

$(function() {
//                $("#drop-zone").draggable();
    var dropZoneId = "drop-zone";
    var buttonId = "clickHere";
    var mouseOverClass = "mouse-over";

    var dropZone = $("#" + dropZoneId);
    var ooleft = dropZone.offset().left;
    var ooright = dropZone.outerWidth() + ooleft;
    var ootop = dropZone.offset().top;
    var oobottom = dropZone.outerHeight() + ootop;
    var inputFile = dropZone.find("input");

    document.getElementById(dropZoneId).addEventListener("dragover", function(e) {
        e.preventDefault();
        e.stopPropagation();
        dropZone.addClass(mouseOverClass);
        var x = e.pageX;
        var y = e.pageY;

        if (!(x < ooleft || x > ooright || y < ootop || y > oobottom)) {
            inputFile.offset({top: y - 15, left: x - 100});
        } else {
            inputFile.offset({top: -400, left: -400});
        }

    }, true);

    if (buttonId != "") {
        var clickZone = $("#" + buttonId);

        var oleft = clickZone.offset().left;
        var oright = clickZone.outerWidth() + oleft;
        var otop = clickZone.offset().top;
        var obottom = clickZone.outerHeight() + otop;

        $("#" + buttonId).mousemove(function(e) {
            var x = e.pageX;
            var y = e.pageY;
            if (!(x < oleft || x > oright || y < otop || y > obottom)) {
                inputFile.offset({top: y - 15, left: x - 160});
            } else {
                inputFile.offset({top: -400, left: -400});
            }
        });
    }

//    document.getElementById(dropZoneId).addEventListener("drop", function(e) {
//        createImage(e.dataTransfer.files[0]);
//        $("#" + dropZoneId).removeClass(mouseOverClass);
//    }, true);

})

//function cargar() {
//    $.ajax({
//        url: 'http://localhost:8080/trabajostitulacion/webresources/Upload/upload',
//        data: new FormData(jQuery('form')[0]),
//        cache: false,
//        contentType: false,
//        processData: false,
//        type: 'POST',
//        success: function(data) {
//            console.log(data);
//        }
//    });
//}