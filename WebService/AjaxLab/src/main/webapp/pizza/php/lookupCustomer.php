<?php

class Customer {
  var $name, $street, $city, $state, $zipCode;

  function Customer($name, $street, $city, $state, $zipCode) {
    $this->name = $name;
    $this->street = $street;
    $this->city = $city;
    $this->state = $state;
    $this->zipCode = $zipCode;
  }

  function getName() {
    return $this->name;
  }

  function getAddress() {
    return $this->street . "\n" .
           $this->city . ", " .
           $this->state . " " .
           $this->zipCode;
  }
}

// Set up some addresses to use
$customers[0] = new Customer("Doug Henderson",
                             "7804 Jumping Hill Lane",
                             "Dallas", "Texas", "75218");
$customers[1] = new Customer("Mary Jenkins",
                             "7081 Teakwood #24C",
                             "Dallas", "Texas", "75182");
$customers[2] = new Customer("John Jacobs",
                             "234 East Rutherford Drive",
                             "Topeka", "Kansas", "66608");
$customers[3] = new Customer("Happy Traum",
                             "876 Links Circle",
                             "Topeka", "Kansas", "66608");

// Pick a customer
srand((double)microtime() * 1000000);
$customerId = rand(0,3);
$customer = $customers[$customerId];

echo $customer->getName() . "\n" .
     $customer->getAddress();

?>
