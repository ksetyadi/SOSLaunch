<?php

class Home extends Controller
{
    function __construct()
    {
        parent::Controller();
    }
    
    function index()
    {
        $sqlString = "SELECT * FROM [YOUR_TIMELINE_HERE] ORDER BY [YOUR_TIMESTAMP_HERE] DESC";
        $sqlQuery = mysql_query($sqlString);
        
        $timeline = array();
        
        while ($sqlData = mysql_fetch_assoc($sqlQuery))
        {
            $timeline[$sqlData['timeline_id']] = $sqlData;
        }
        
        $data['timeline'] = $timeline;
        
        $this->load->view("pre");
        $this->load->view("inpage", $data);
        $this->load->view("footer");
    }
}