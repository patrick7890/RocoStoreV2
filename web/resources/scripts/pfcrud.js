function handleSubmit(xhr, status, args, dialog) {
    var jqDialog = jQuery('#'+dialog.id);
    if(args.validationFailed) {
        jqDialog.effect('shake', { times:3 }, 100);
    } else {
        dialog.hide();
    }
}
function fixPFMDialogs() {
    jQuery("body > div[data-role='page']").each(function (i) {
        var pageId = jQuery(this).attr("id");
        jQuery("body > div[id*='" + pageId + "'][class*='ui-popup']").appendTo("#" + pageId);
    });
}
function Keygen()
{
    var d = new Date().getTime();

    if (window.performance && typeof window.performance.now === "function")
    {
        d += performance.now();
    }

    var uuid = 'xxxx-xxxx-yxxx-xxxx'.replace(/[xy]/g, function (c)
    {
        var r = (d + Math.random() * 16) % 16 | 0;
        d = Math.floor(d / 16);
        return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
    });

    document.getElementById("CodigoCreateForm:codigo").value  = uuid.toUpperCase();
    document.getElementById("CodigoCreateForm:activo").value  = 1;
}
