<?php

include("order.php");
include("delivery.php");

// Error checking
$order = $_REQUEST['order'];
$address = $_REQUEST['address'];
if (strlen($order) <= 0) {
  header("Status: No order was received.", true, 400);
  echo " ";
  exit;
}
if (strlen($address) <= 0) {
  header("Status: No address was received.", true, 400);
  echo " ";
  exit;
} 
  

// Place the order
$pizzaOrder = new PizzaOrder($order, $address);
$pizzaOrder->cookOrder();
$pizzaOrder->prepOrder();

// Deliver the order
$delivery = new Delivery($pizzaOrder);
$delivery->deliver();
$deliveryTime = $delivery->getDeliveryEstimate();

echo $deliveryTime;
?>

