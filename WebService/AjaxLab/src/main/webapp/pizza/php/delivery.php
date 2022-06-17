<?php

class Delivery {
  var $status, $pizzaOrder, $deliveryEstimate;

  function Delivery($pizzaOrder) {
    $this->pizzaOrder = $pizzaOrder;
    $this->status = "Received Order";
  }

  function deliver() {
    if ($this->pizzaOrder->getStatus() == "Prepped for Delivery") {
      srand((double)microtime() * 1000000);
      $this->deliveryEstimate = rand(2, 10);
      $this->status = "Delivering Order";
      for ($i = 0; $i<$deliveryEstimate; $i++) {
        // Deliver pizza
      }
      $status = "Pizza Delivered.";
    } else {
      $this->pizzaOrder->prepOrder();
      $this->deliver();
    }
  }
  
  function getStatus() {
    return $this->status;
  }

  function getDeliveryEstimate() {
    return $this->deliveryEstimate;
  }
}
