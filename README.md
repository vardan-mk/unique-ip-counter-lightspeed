# unique-ip-counter-lightspeed
## Lightspeed task to find unique IPv4 addresses in huge file.

### Here you can find solution to this task written in Java 17 in [Main.java](src%2Fmain%2Fjava%2Fcom.lightspeed.ipcounter%2Fexample%2FMain.java) class and supported [UniqueIpCounter.java](src%2Fmain%2Fjava%2Fcom.lightspeed.ipcounter%2Fexample%2FUniqueIpCounter.java) class, 
### also additional class __[IPAddressGenerator.java](src%2Fmain%2Fjava%2Fcom.lightspeed.ipcounter%2Fexample%2FIPAddressGenerator.java)__ class 
#### - which generate your provided amount of random ip's
#### - make your provided amount of them repeatable
#### - write them to __ipv4_addresses.txt__ file in [resources](src%2Fmain%2Fresources) folder

The main logic in solution which help to solve this task with less memory and in short time read line 
is to read line by line as this approach doesn't load all file content to memory, take to action the fact
that IPv4 addresses consist of 4 octets each one in range of __0-255__, so in java we can use short primitive
to consume less memory and create 4 dimension matrix of __booleans__ each by size of __256__ and 
fill it with our octets break down by ip string, so each time there will be written in same cell.
after that we can traverse with 4 for loops embedded in each other, from first looks it increase 
our time complexity with __O(N**4)__, but as all loops running from 0 to 255 which is small amount, 
so we can say that in this case the complexity will be approximately __O(256**4)__ which is around 4.3 billion.

Finally in the last loop we check does the current cell of our __4x4 matrix__ equal to __true__ or not, 
and of yes then increase our count of unique values by __1__. 
