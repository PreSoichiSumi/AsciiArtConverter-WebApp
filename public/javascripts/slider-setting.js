/**
 * Created by s-sumi on 2016/03/25.
 */
$("#slider").slider();
$("#slider").on("slide", function(slideEvt) {
    $("#accuracy").text(slideEvt.value);
});