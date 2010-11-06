<?php

class GetAllData extends Controller
{
    function __construct()
    {
        parent::Controller();
    }
    
    function index()
    {
        $sqlString = "SELECT * FROM [YOUR_TIMELINE_HERE] ORDER BY [YOUR_TIMESTAMP_HERE] DESC";
        $sqlQuery = mysql_query($sqlString);
        
        $counter = 0;
        
        while ($sqlData = mysql_fetch_assoc($sqlQuery))
        {
            $result[$sqlData['timeline_id']] = $sqlData;
        }
        
        echo json_encode($result);
        exit(0);        
    }
}