const express = require('express')
const { Client } = require('pg')
const client = new Client({
    user: 'postgres',
    host: 'localhost',
    database: 'student_management_system',
    password: '12345678',
    port: 5432,
});
client.connect();
const bodyParser = require('body-parser')
var app = express();
app.use(bodyParser.json())
app.get("/", async(req, res) => {
    var result = (await client.query("select * from student where id=$1",[req.body["id"]])).rows
    res.status(200).send(result)
})
app.listen(8080)