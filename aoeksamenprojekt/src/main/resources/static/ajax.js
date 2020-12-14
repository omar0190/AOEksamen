function isjQuery() {

    if (jQuery() != undefined) {
        alert("Jquery is working");
    }

}


function preventdelete(slet) {
    slet.submit(function (event) {
        event.preventDefault();
        deletestilling($("#ID").val(), $("#title").val());
    })
}
var i = 1;

function deletestilling(id, title) {


    if(i < 5){
        var obj = {};
        obj["ID"] = id;
        obj["title"] = title;


        $.ajax({
            url: "/sletstilling",
            type: "POST",
            contentType: "Application/JSON",
            data: JSON.stringify(obj),
            success: function (data) {
                console.log("submit clicked" + id);
            },
            error: function (data) {
                console.log("delete stilling not working");

            }
        });

        i++;
    }



}