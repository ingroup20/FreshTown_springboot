

//function incrementQuantity() {
//    const input = document.getElementById('quantityInput');
//    input.value = parseInt(input.value, 10) + 1;
//}
//
//function decrementQuantity() {
//    const input = document.getElementById('quantityInput');
//    const currentValue = parseInt(input.value, 10);
//    input.value = currentValue > 1 ? currentValue - 1 : 1;
//}

//$("button#buyit").click(function(){
////	mealNo = $(this).closest("div").find("input#mealNo").val();
//
//    let qty = $(this).closest("div").find("input#quantityInput").val();
//    $("input#qty_back").val(qty);
//
//});

//====喜好選擇==================================================================

$(document).ready(function() {
	
	$("button#sentMealOrder").click(function(){
	    let collectedValues = collectSelectValues();
	    $("input#selectDate").val(collectedValues.selectValues);
	    console.log($("input#selectDate").val());
	});

	function collectSelectValues() {
	        var selectValues = [];
	        // Iterate over all elements with class 'select-option'
	        $('.select-option').each(function() {
	            var selectedValue = $(this).val();
	            selectValues.push(selectedValue);
	        });
	
	        return { selectValues: selectValues };
	    }
	



	function submitForm() {
	    // Get the form element
	    var form = document.getElementById("checkMealDetail");
	    // Submit the form
	    form.submit();
	}
	


	$("#continue_button").click(function(){
		$("#towhere").val("b");
		submitForm() ;
	});
	
	$("#next_button").click(function(){
		$("#towhere").val("c");
	    submitForm() ;
//	    window.location.href = '/freshtown_springboot/cFunction/cartPage';
	});
});