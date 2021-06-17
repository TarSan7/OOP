<?php

class Nursery{
    public $city;
    public $title;
    public $mark;
    const MAX_MARK = 10;

    function __construct($c = '-', $t = '-', $m = '-'){
        $this->city = $c;
        $this->title = $t;
        $this->mark = $m;
    }

    public function change_info($what, $ch){
        $this->$what = $ch;
    }

    public function quality(){
        if($this->mark === '-' ) return 'This nursery has no rating.';
        elseif($this->mark === self::MAX_MARK) return 'This nursery have excellent quality!';
        elseif($this->mark / self::MAX_MARK >= 0.7) return 'This nursery have a good quality.';
        elseif($this->mark / self::MAX_MARK >= 0.3) return 'This nursery have a middle quality.';
        else return 'This nursery have a bad quality.';
    }

    public function display(){
        echo 'Information about current nursery: <br><ul><li>City: '.$this->city.'<li>Name: '.$this->title.
        '<li>Mark/From: '.$this->mark.'/'.self::MAX_MARK.'<br>'.$this->quality().'</ul><br>';
    }

    function __toString(){
        return $this->city.' '.$this->title.' '.$this->mark.'<br>';
    }

    function __destruct(){}
}

class Animals extends Nursery{
    public $types;
    public $num_of_types = 0;

    function __construct($c = '-', $t = '-', $m = '-'){
        parent::__construct($c, $t, $m);
    }

    public function add_type($type, $kol){
        $this->types[$this->num_of_types][0] = $type;
        $this->types[$this->num_of_types][1] = $kol;
        $this->num_of_types++;
    }

    public function display(){
        parent::display();
        echo 'Information about animal in current nursery:<br>';
        for($i = 0; $i < sizeof($this->types); $i++){
            echo '<ul><li>Type of animal: ' . $this->types[$i][0] . '<br>Number of this animal: ' . $this->types[$i][1] . '</ul>';
        }
    }

    function __destruct(){
        parent::__destruct();
    }
}

class Subject extends Animals{
    public $description;
    public $self_num = 0;

    function __construct($c = '-', $t = '-', $m = '-'){
        parent::__construct($c, $t, $m);
    }

    public function descript($type, $name, $age, $descr){
        $this->description[$this->self_num] = implode('/', [$type, $name, $age, $descr]);
        $this->self_num++;
    }

    public function display(){
        parent::display();
        for ($i = 0; $i < $this->self_num; $i++) {
            $arr = explode('/', $this->description[$i]);
            echo 'Information about animal: <br>Type - ' . $arr[0] .' <br>Name - ' . $arr[1] . ' <br>Age - ' . $arr[2] . ' <br>' . $arr[3]. '<br>';
        }
    }
}

class Rating{
    private static $instance;
    private $mark;

    private function __construct(){}
    private function __clone(){}
    private function __wakeup(){}

    public static function getInstance(){
        if(self::$instance === null){
           self::$instance = new self();
        }
        return self::$instance;
    }
    public function setMark($m){
        if($this->mark === null) {
            $this->mark = $m;
        }
    }
    public function getMark(){
        return $this->mark;
    }

}

$exobj = new Subject('Kyiv', 'Hope', 7);
$exobj->add_type('Cats', 15);
$exobj->add_type('Dogs', 10);
$exobj->add_type('Horses', 3);
$exobj->descript('cat', 'Barsik', 20, 'Friendly cat that loves hugs and attention)');
$exobj->descript('dog', 'Bobik', 20, 'Very soshial boy. Likes eating and playing games.');
$exobj->descript('horse', 'Spesha', 20, 'Beautifull and lovely girl. As fast as winter!');
$exobj->display();

$myMark = Rating::getInstance();
$myMark->setMark(8);
$tryFake = Rating::getInstance();
$tryFake->setMark(1);
echo 'Is this object really unic?'.($myMark === $tryFake ? ' Yes!' : ' No...');
echo '<br><b>My mark of this nersery is: '.$myMark->getMark().'/'.$exobj::MAX_MARK.'.</b>';
?>