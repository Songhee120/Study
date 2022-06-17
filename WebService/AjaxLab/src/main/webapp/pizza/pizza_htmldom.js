var request = new XMLHttpRequest();

function getCustomerInfo() {
  var phone = document.getElementById("phone").value;
  if (phone == "") {
	document.getElementById("address").value = "";
	document.getElementById("order").value = "";
  }
  else {
	var url = "lookupCustomer.jsp?phone=" + escape(phone);
	request.open("GET", url, true);
	request.onreadystatechange = updatePage;
	request.send(null);
  }
}

function updatePage() {
  if (request.readyState == 4) {
    if (request.status == 200) {
      /* Get the response from the server */
      var customerAddress = request.responseText;
      /* Update the HTML web form */
      alert("responseText: " + customerAddress);
      document.getElementById("address").value = customerAddress;
    } else
      alert("Error! Request status is " + request.status);
  }
}

function submitOrder() {
  var phone = document.getElementById("phone").value;
  var address = document.getElementById("address").value;
  var order = document.getElementById("order").value;
  var url = "placeOrder.jsp";
  request.open("POST", url, true);
  request.onreadystatechange = showConfirmation;
  request.setRequestHeader("Content-Type",
                           "application/x-www-form-urlencoded");
  request.send("phone=" + escape(phone) +
               "&address=" + escape(address) +
               "&order=" + escape(order));
}

function showConfirmation() {
  if (request.readyState == 4) {
    if (request.status == 200) {
      var response = request.responseText;
      // Locate form on page
      var mainDiv = document.getElementById("main-page");
      var orderForm = document.getElementById("order-form");

      // Create some confirmation text
      pElement = document.createElement("p");
      textNode = document.createTextNode("Your order should arrive within " +
        response + " minutes. Enjoy your pizza!");
      pElement.appendChild(textNode);

      // Replace the form with the confirmation
      // mainDiv.replaceChild(pElement, orderForm);
      mainDiv.appendChild(pElement);
    } else {
      var message = request.getResponseHeader("Status");
      if ((message == null) || (message.length <= 0)) {
        alert("Error! Request status is " + request.status);
      } else {
        alert(message);
      }
    }
  }
}