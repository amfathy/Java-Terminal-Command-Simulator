package cmd;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.InvalidPathException;

public class cmd {
    public static class Terminal {
        private Parser parser;
        String currentWorkingDirectory;
        String pathh ;
        Terminal() {
           
              this.currentWorkingDirectory = System.getProperty("user.dir");
        }
        
        public void setParser(Parser p){
            this.parser = p;
        }
        //-----------  ID: 20190342  ------------------------
        public void echo(String[] args) {
            for(int i=1;i<args.length;i++){
                System.out.println(args[i]);
            }
        }

          public String ls() throws IOException {
        String lsContents = "";
        File file = null;
        String[] filePaths;


        file = new File(currentWorkingDirectory);
        filePaths = file.list();

        for(String path:filePaths) {
            System.out.println(path);
            lsContents += path + "\n";
        }

        return lsContents;
    }

        public void ls_r(){
            File f1 = new File("E:\\New folder");
            String[] array = f1.list();
            for(int i = array.length-1; i >= 0; --i){
                System.out.println(array[i]);
            }
        }

        public void rmdir(String[] args){
            String str1 = "E:\\New folder\\";
            File f = new File(str1+args[1]);
            if (!f.exists())
                System.out.println("No such directory exists");
            else if (f.isFile())
                System.out.println("Cannot delete file");
            else if (!f.delete())
                System.out.println("Cannot delete non-empty directory.");
        }
        //-----------  ID: 20190061  ------------------------
        public static void touch (String[] args) throws IOException
        {
           
            File file1 = new File(args[1]);
            if (!file1.exists()) {
                new FileOutputStream(file1).close();
            }
        }
        public static void cp(String[] args)
                       throws Exception {
                   String str1 = "E:\\New folder\\";
                   try (FileInputStream in = new FileInputStream(str1 + args[1]);
                        FileOutputStream out = new FileOutputStream(str1 + args[2])) {
                       int n;
                       while ((n = in.read()) != -1) {
                           out.write(n);
                       }
                   }
               }     
      public String pwd() {

        System.out.println(currentWorkingDirectory);
        return currentWorkingDirectory;
    }

        public static String append (String[] args)
        {
            String str1 = "E:\\New folder\\";
            try (FileWriter f = new FileWriter(str1 + args[1], true);
                 BufferedWriter b = new BufferedWriter(f);
                 PrintWriter p = new PrintWriter(b);) {
            } catch (IOException i)
            { i.printStackTrace(); }
            return "Append Process Was Success.";
        }
        //-----------  ID: 20190068  ------------------------
         public String getCurrentWorkingDirectory() {
        return currentWorkingDirectory;
    }
         
    public String getPath(String filename){
        File file = new File(filename);

        if(file.isAbsolute() || (file.isDirectory())){
            return filename;
        }
        else {
            return currentWorkingDirectory  + filename;
        }
    }

       public void rm(String[] args)
        {
            try
            {
                Files.deleteIfExists(Paths.get(args[1]));
            }
            catch (IOException x)
            {
                System.out.println("File Doesn't Exist.");

            }
        }


      public void mkdir(String file){
    
        file = getPath(file);
        try {
            Path path = Paths.get(file);
            Files.createDirectories(path);
      

        } catch(IOException e) {
        }
    }
        public void cat(String[] args) throws IOException {

            if (args.length==1)
            {
                File file = new File (args[0]);
                Scanner read = new Scanner(file);
                while (read.hasNextLine())
                {
                    System.out.print(read.nextLine());
                }
                read.close();
            }

            if (args.length==2)
            {
                File one = new File(args[0]);
                File two = new File(args[1]);
                Scanner read = new Scanner(one);
                String one_content= "";
                while (read.hasNextLine())
                {
                    one_content = one_content.concat(read.nextLine());

                }
                FileWriter tw = new FileWriter(args[1]);
                tw.write(one_content);
                tw.close();
                Scanner re = new Scanner(two);
                while (re.hasNextLine())
                {
                    System.out.print(re.nextLine());
                }
            }
        }

       public void cd(String directoryName){
        try {
            
            File file = new File(directoryName);
            if(directoryName.equals("..")){
                String s = pathh;
                String perpath;
            perpath= this.currentWorkingDirectory.replaceAll(s, "");
         
            this.currentWorkingDirectory = perpath;
            
            }
            else if((file.isAbsolute()) || (file.isDirectory() )){
                this.currentWorkingDirectory =  directoryName;
            }
            else{
                File f = new File(this.currentWorkingDirectory+directoryName);
                if(f.exists()){
                    this.currentWorkingDirectory += directoryName;
                }
                else {
                    System.out.println("Cannot find path");
                }
            }
        } catch (InvalidPathException | NullPointerException ex) {
            System.out.println("Invalid path");
        }
    }

        
        //--------------------------------------------------------------------
        public void chooseCommandAction() throws Exception {
            String[] args = parser.getArgs();
            String nameOfCommand = parser.getCommandName();

            if(nameOfCommand.equals("ls")){
                if(args.length >1 && args[1].equals("-r")){
                    nameOfCommand += " -r";
                }
            }
            switch (nameOfCommand){
                //-----------  ID: 20190342  ------------------------
                case "echo":{
                    echo(args);
                    break;
                }
                case "ls":{
                    ls();
                    break;
                }
                case "ls -r":{
                    ls_r();
                    break;
                }
                case "rmdir":{
                    rmdir(args);
                    break;
                }
                //-----------  ID: 20190061  ------------------------
                case "touch":{
                    touch(args);
                    break;
                }
                case "cp":{
                    cp(args);
                    break;
                }
                case "pwd":{
                    System.out.println(pwd());
                    break;
                }
                case "append":{
                    append(args);
                    break;
                }
                //-----------  ID: 20190068  ------------------------
                case "rm":{
                    rm(args);
                    break;
                }
                case "mkdir":{
                mkdir(args[1]);
                    break;
                }
                case "cat":{
                    cat(args);
                    break;
                }
                case "cd":{
                    cd(args[1]);
                    break;
                }
                default:
                    break;
            }
        }
        
    }
    //---------------------------------------------------------------------
    public static class Parser {
        String[] array;
        String commandName;

        public String[] getArgs(){return array;}

        public String getCommandName(){return commandName;}
        
          Terminal terminal = null;

    public Parser() throws IOException {
        terminal = new Terminal();
    }
    
          public String currentWorkingDirectory(){
        return terminal.getCurrentWorkingDirectory();
    }
          
        public boolean parse(String input){
            array = input.split(" ");
            commandName = array[0];
            String[] allCommands = {"mkdir","cat","touch","ls -r","pwd",
                    "echo","cp","rm","rmdir",
                    "cd","ls","append"};
            for(int i=0;i<allCommands.length;i++){
                if(Objects.equals(commandName, allCommands[i])){
                    return true;
                }
            }
            return false;
        }
        
        
        
    }
    //---------------------------------------------------------------------
    public static void main(String[] args) throws Exception {
        Parser P1 = new Parser();
        Terminal T1 = new Terminal();

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        while (!line.equals("exits")) {
            boolean flag = P1.parse(line);
            if (flag) {
                T1.setParser(P1); //to check the command
                T1.chooseCommandAction(); //to choice one command from switch
            }
            else{
                System.out.println(line + " is not recognized.");
            }
            line = in.nextLine();
            
        }
    }
}
