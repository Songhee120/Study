<?php

class PizzaOrder {
  var $order, $address, $status;
  
  function PizzaOrder($order, $address) {
    $this->order = $order;
    $this->address = $address;
    $this->status = "In Progress";
    srand((double)microtime() * 1000000);
  }

  function cookOrder() {
    $this->status = "Cooking";
    $cookTime = rand(0, 100000);
    for ($i=0; $i<$cookTime; $i++) {
      // cook order
    }
    $this->status = "Cooked and ready for prepping";
  }

  function prepOrder() {
    $this->status = "Preparing for Delivering";
    $prepTime = rand(0, 100000);
    for ($i=0; $i<$prepTime; $i++) {
      // prepare for delivery
    }
    $this->status = "Prepped for Delivery";
  }

  function getStatus() {
    return $this->status;
  }
}

?>
