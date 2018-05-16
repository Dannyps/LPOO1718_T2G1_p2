<?php

class Controller{

    private $link;

    public function __construct($link){
        $this->link = $link;
    }

    public function getHighScores($request){
        $res = mysqli_query($this->link, "SELECT * FROM scores ORDER BY score DESC LIMIT 15");
        echo json_encode(mysqli_fetch_assoc($res));
        return 200;
    }

    public static function postHighScores($request, $p){
        var_dump($p);
    }
}