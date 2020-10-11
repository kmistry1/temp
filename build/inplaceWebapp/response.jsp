<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src=
"https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js">
  </script>
  <style>
      body {
          margin: 2%;
          font-size: 120%;
      }

      th,
      td {
          padding: 20px;
      }
  </style>
    <head>
        <title>Hello Page</title>
    </head>
    <body ng-app="myApp" ng-controller="ListController">
        <h2>Hello, ${user}!</h2>

        <table border=1>
                <thead>
                    <tr>
                        <th>S.No</th>
                        <th>Name</th>
                        <th>Country</th>
                        <th>Grand Slams</th>
                        <th>Active</th>
                    </tr>
                </thead>
                <tr ng-repeat="item in itemsDetails">
                    <td> {{item.sno}} </td>
                    <td> {{item.name}} </td>
                    <td> {{item.country}} </td>
                    <td> {{item.grandslams}} </td>
                    <td> {{item.active}} </td>
                </tr>
            </table>
    </body>

   <script type="text/javascript">
   var app = angular.module('myApp', []);
   app.controller('customersCtrl', function($scope, $http) {
       $http.post("InfoServletPath")
       .then(function (response) {
          $scope.Info = response.data.Info;
       });
   });
   </script>
</html>