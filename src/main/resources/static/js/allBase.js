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