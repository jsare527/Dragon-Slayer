# DragonSlayer Pull List Program
Capstone Project UNO 2023

**Important Facts:**
Development was done using IntelliJ IDE and deployment must be done on a Windows OS.

# How to Deploy Program

1. Create a Maven Configuration to run clean install -DskipTests=true
   - This will create DragonSlayer.jar
2. Using command prompt in IDE in the jar location, run java -jar DragonSlayer.jar
   - This must run successfully for deployment
3. Run jpackage --name "DragonSlayerVX" --input . --main-jar DragonSlayer.jar
   - X represents the version of Dragon Slayer being deployed
   - This will produce the software installer
4. Copy the DragonSlayerVX installer to a different location
5. Run the installer as admin
6. Go to program files to find DragonSlayerVX folder and run application as admin
   - This should produce the necessary derby and settings.ini files

**Important Note**: For deployment to sponsor, the program folder will have to be zipped and sent due to them not having admin privileges on their computers.