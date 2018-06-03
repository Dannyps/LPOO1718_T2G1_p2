<?php

class Controller{

    private $link;

    public function __construct($link){
        $this->link = $link;
    }

    public function getHighScores($request){
        $res = mysqli_query($this->link, "SELECT name, score FROM scores ORDER BY score DESC LIMIT 15");
        echo json_encode(mysqli_fetch_all($res, MYSQLI_ASSOC));
        return 200;
    }

    public function postHighScore($request, $p){
        /* create a prepared statement */
        if ($stmt = mysqli_prepare($this->link, "INSERT INTO scores VALUES (NULL, ?, ?, NOW(), ?)")) {

            /* bind parameters for markers */
            mysqli_stmt_bind_param($stmt, "sis", $p['name'], $p['score'], $_SERVER['REMOTE_ADDR']);

            /* execute query */
            mysqli_stmt_execute($stmt);
			
            return 204;
        }else{
            var_dump($stmt);
            return 503;
        }        
    }
}