<?php

class Controller{

    private $link;

    public function __construct($link){
        $this->link = $link;
    }

    public static function getHighScores($request, $g){
        var_dump($g);
        return 201;
    }

    public static function postHighScores($request, $p){
        var_dump($g);
    }
}