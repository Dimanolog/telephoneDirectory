<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Uploading user's JSON files</title>
</head>

<body>
<form name="uploadingForm" enctype="multipart/form-data" action="/uploadUserFile" method="POST">
    <p>
        <input id="fileInput" type="file" name="file">
    </p>
    <p>
        <input type="submit" value="Upload file">
    </p>
</form>
</body>
</html>