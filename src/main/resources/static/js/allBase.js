$("button.search_select").on("click",function(e){
    if($("div.store_detail_class").hasClass("-none")){
        $("div.store_detail_class").removeClass("-none");
    }else{
        $("div.store_detail_class").addClass("-none");
    }

})


$("button.phone-btn").on("click",function(e){
    if($("ul.nav").hasClass("-move")){
        $("ul.nav").removeClass("-move");
    }else{
        $("ul.nav").addClass("-move");
    }
})

$(document).ready(function() {
		    // 当鼠标悬停在 #myBlock 上时，改变底色
		    $(".li_item").mouseover(
		        function() {
		            $(this).addClass("hovercolor");
		        }
		    );
		    $(".li_item").mouseout(
		        function() {
		            $(this).removeClass("hovercolor");
		        }
		    );
		});