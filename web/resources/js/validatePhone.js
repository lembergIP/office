jQuery(function($){
    $("#phoneNumber").mask("(999)999-99-99");
});
jQuery(document).ready(function($){
    $cf = $('#phoneNumber');
    $cf.blur(function(e){
        phone = $(this).val();
        phone = phone.replace(/[^0-9]/g,'');
        if (phone.length != 10)
        {
            alert('Phone number must be 10 digits.');
            return false;
        }
    });
});
