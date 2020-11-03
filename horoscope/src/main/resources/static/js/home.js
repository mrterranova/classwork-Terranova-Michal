function getHoroscope(){
        let name = getName();
        let birthday = getBirthday();

    $.ajax({
        url: 'http://localhost:8080/api/reading?name='+name+'&birthday='+birthday,
        success: function ( reading ) {
            console.log(reading);
            let message = "<p> Hello "+ reading.name+ "! This is your message: " + reading.message+" because you are a " + reading.sign +"</p>";
            $("#results").empty();
            $("#results").append(message);

        },
        error: function ( a, b, c){
            console.log(a);
        }
    });
}

function getName(){
    let name = $("#name").val();
    return name;
}

function getBirthday(){
    let date = $("#date").val();
    return date;
}