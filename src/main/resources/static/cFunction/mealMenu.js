
 let mealNo ;
//數量+-按鈕
function incrementQuantity() {
    const input = document.getElementById('quantityInput');
    input.value = parseInt(input.value, 10) + 1;
}

function decrementQuantity() {
    const input = document.getElementById('quantityInput');
    const currentValue = parseInt(input.value, 10);
    input.value = currentValue > 1 ? currentValue - 1 : 1;
}

$("button#buyit").click(function(){
//	mealNo = $(this).closest("div").find("input#mealNo").val();

    let qty = $(this).closest("div").find("input#quantityInput").val();
    $("input#qty_back").val(qty);

});

//====喜好選擇==================================================================


//
//function sendRequest() {
//
//	
//var url = window.location.pathname +window.location.search + '&mealNo=' + encodeURIComponent(mealNo) + '&param2=' + encodeURIComponent(parameter2);
//	
//	
//        $.ajax({
//            type: 'GET', 
//            url: '/cFunction/storeMenu', //後端接收
//            success: function(data) {
//                customizedListData(data);
//                $('#customizedTable').modal('show');
//            },
//            error: function(error) {
//                console.error('Error:', error);
//            }
//        });
//    }
//
//    function customizedListData(data) {
////        $('#customizedTable').empty();//清除原table
//
//        // 添加標籤
//        data.forEach(function(customizedVO) {
//            var row = $('<tr></tr>');
//            row.append('<td>' + customizedVO.customizedItemsVO.custedDtlName + '</td>');
//            row.append('<td><select><option value="' + customizedVO.customizedItemsVO.customizedDetailVO.custedItemsNo + '">' + customizedVO.customizedItemsVO.customizedDetailVO.custedDtlName + '</option></select></td>');
//            $('#customizedTable').append(row);
//        });
//    }
//
//    function submitForm() {
//        // You can add logic here to submit the form with selected values if needed
//        // Example: $('#yourFormId').submit();
//    }