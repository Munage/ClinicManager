<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="report" />
    <title>Report</title>
</head>
<body>

    <table>
        <thead>
            <tr>
                <td>Clinic</td>
                <td>NEVIRAPINE</td>
                <td>STAVUDINE</td>
                <td>ZIDOTABINE</td>
            </tr>
        </thead>
        <g:each in="${stockLevels}">
            <tr>
                <td>${it.clinic}</td>
                <td>${it.Nevirapine}</td>
                <td>${it.Stavudine}</td>
                <td>${it.Zidotabine}</td>
            </tr>
        </g:each>
    </table>

</body>
</html>