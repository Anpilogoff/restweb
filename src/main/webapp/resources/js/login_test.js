var  line2, line3, line4, line5, line6;

line4 = document.getElementById('line-4');
line5 = document.getElementById('line-5');
line6 = document.getElementById('line-6');


function line1resize() {

        var line1 = document.getElementById('line1');
setTimeout(line2resize,2500);
setTimeout(line3resize, 2510);
line1.animate([{width: '1px'},{width: '500px'}],{duration: 2500, fill: "forwards"});


}

function line2resize(){
    line2 = document.getElementById('line2');
    line2.animate([{width: '1px'},{width: '22%'}],{duration:40, fill: "forwards"});

}

function line3resize(){
    line3 = document.getElementById('line3');
    line3.animate([{width:'1px'},{width: '410px'}],{duration:5, fill: "forwards"});
}



window.addEventListener('load',  function(){
    line1resize();
});




