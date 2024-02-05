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
		    
		    
				    $(".nav_item.li_item.menu").hover(
		        function(){
		            $(".dropdown_menu.-none").removeClass("-none");
		        },
		        function(){
		            $(".dropdown_menu").addClass("-none");
		        }
		    );
		    
		    
		    
//		    ======================================
 $(document).ready(function () {
        	$("span.restDay").each(function () {
        		$("span.restDay").hide();
                var number = $(this).text().trim();
                var result = convertToText(number);
                $(this).next().text(result);

            });
        	
        });
        
function convertToText(number) {
            var numberStr = number.toString().split('').reverse().join('');
            var result = '';

            for (var i = 0; i < numberStr.length; i++) {
                var dayState = parseInt(numberStr.charAt(i));
                if(dayState===0){
                	switch(i){
                		case(0):
                			 var day="星期一,";
                			result += day;
                			 continue;
                		case(1):
                   			 var day="星期二,";
                		result += day;
                			continue;
                		case(2):
                   			 var day="星期三,";
                		result += day;
                			continue;
                		case(3):
                   			 var day="星期四,";
                			continue;
                		case(4):
                   			 var day="星期五,";
                			continue;
                		case(5):
                   			 var day="星期六,";
                			continue;
                		case(6):
                   			 var day="星期日";
                		result += day;
                			continue;
                		case(7):
                  			 var day="";
               			continue;
                		}             	
                }else{
                	var day="";
                }
                
                result += day;
                if (i < numberStr.length - 1) {
                    result += ' ';
                }
            }
            return result;
        }
		    
		    
		});
		
		
			
       

        

		
		
		