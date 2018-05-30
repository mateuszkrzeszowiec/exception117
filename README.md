# exception117
Project demonstrating CWE ID 117 when logging exceptions, including nested exceptions.

In order to observer CWE ID 117 run the application and use following simple vector: http://localhost:8080/?dataFromRequest=data%0a%0ddata
It's somewhat difficult to resolve the problem here assuming that one wants to log exceptions - recursive cleanup of Exception#getMessage would have to be put in place
