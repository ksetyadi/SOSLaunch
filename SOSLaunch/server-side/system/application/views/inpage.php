<script type="text/javascript" src="javascripts/inpage.js"></script>

<div id="header_container">
    <div id="header_container_left">
        SOS Launch Crisis Center
    </div>
    <div id="header_container_right">
        by: NASA, Google, Microsoft, Yahoo
    </div>
</div>

<div id="map_container">
    <div id="close_button_container">
        [<a href="javascript:void(0);" onclick="closeMap();">X</a>]
    </div>
    <div id="map_canvas" align="center" style="width: 100%; height: 100%">
        <img src="images/ajax-loader.gif" width="25" alt="loader" />
    </div>
</div>

<div id="contents">
<table id="tblResult" width="100%">
    <thead>
        <tr>
            <th>ID</th>
            <th>Timestamp</th>
            <th>Phone Number</th>
            <th>Latitude</th>
            <th>Longitude</th>
            <th>Location</th>
            <th>Message</th>
        </tr>
    </thead>
    <tbody>

    <?php
    
    foreach ($timeline as $dataId => $dataArr)
    {
        $datetime = new DateTime($dataArr['timestamps']);
        
        ?>
        
        <tr>
            <td align="center"><?php echo $dataArr['timeline_id']; ?></td>
            <td align="center"><?php echo $datetime->format("r"); ?></td>
            <td align="center"><?php echo $dataArr['phone_number']; ?></td>
            <td align="center"><?php echo $dataArr['latitude']; ?></td>
            <td align="center"><?php echo $dataArr['longitude']; ?></td>
            <td align="center">
                <a href="javascript:void(0);" onclick="viewMap('<?php echo $dataArr['latitude']; ?>', '<?php echo $dataArr['longitude']; ?>', '<?php echo $dataArr['message']; ?>');">
                view map
            </td>
            <td><?php echo $dataArr['message']; ?></td>
        </tr>
        
        <?php
    }
    
    ?>
    
    </tbody>    
</table>
</div>