public class FileEdit {
    /**
     * Takes a username as input and checks if it exists. Returns true
     * if taken and false if not.
     * @param  username Username of given user
     * @return boolean representing whether the username is taken
     * @author Charles Greiner
     */
    public boolean checkUsername(String username, String filename) {
        try {
            // check if file exists
            File f = new File(filename);
            if(!f.exists()) {
                // create new file if it does not exist
                f.createNewFile();
            }
            // open file stream
            FileReader fr = new FileReader(filename);
            BufferedReader bfr = new BufferedReader(fr);
            // search for user
            String line;                                        /* stores the current line */
            String lineArray[] = new String[4];                 /* stores each sequential line */
            while ((line = bfr.readLine()) != null) {
                lineArray = line.split(",");
                if (lineArray[0].equals(username)) {
                    return true;
                }
            }
            return false;
        } catch (Throwable e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Takes a username and password as input and checks the username and
     * password match. Returns true if taken and false if not.
     * @param  username username of the new user
     * @param  password password of current user
     * @return boolean representing whether the username and password match
     * @author Charles Greiner
     */
    public boolean checkPassword(String username, String password, String filename) {
        try {
            // check if file exists
            File f = new File(filename);
            if(!f.exists()) {
                // create new file if it does not exist
                f.createNewFile();
            }
            // open file stream
            FileReader fr = new FileReader(filename);
            BufferedReader bfr = new BufferedReader(fr);
            // search for user
            String line;                                        /* stores the current line */
            String lineArray[] = new String[4];                 /* stores each sequential line */
            while ((line = bfr.readLine()) != null) {
                lineArray = line.split(",");
                if (lineArray[0].equals(username) && lineArray[1].equals(password)) {
                    bfr.close();
                    fr.close();
                    return true;
                }
            }
            bfr.close();
            fr.close();
            return false;
        } catch (Throwable e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Removes a user from the file.
     * @param name     current user's name
     * @param username current user's username
     * @param password current user's password
     * @param filename current user's filename
     * @param inFile   filename of the file containing user data
     * @author         Charles Greiner
     */
    public void removeUser(String name, String username, String password, String filename, String inFile) {
        ArrayList<String> userData = new ArrayList<String>();
        // read file
        try {
            // check if file exists
            File f = new File(inFile);
            if(!f.exists()) {
                // create new file if it does not exist
                f.createNewFile();
            }
            // open file stream
            FileReader fr = new FileReader(inFile);
            BufferedReader bfr = new BufferedReader(fr);
            // search for user
            String line;                                        /* stores the current line */
            while ((line = bfr.readLine()) != null) {
                userData.add(line);
            }
            bfr.close();
            fr.close();
            // remove user
            userData.remove(userData.indexOf(username + "," + password + "," + name + "," + filename));
        } catch (Throwable e) {
            e.printStackTrace();
        }
        // write file
        try {
            // check if file exists
            File f = new File(inFile);
            if(!f.exists()) {
                // create new file if it does not exist
                f.createNewFile();
            }
            // open file stream in append mode
            FileWriter fw = new FileWriter(inFile, false);
            BufferedWriter bfw = new BufferedWriter(fw);
            // write to file
            for (int i = 0; i < userData.size(); i++) {
                bfw.write(userData.get(i));
                bfw.newLine();
            }
            // close file stream
            bfw.close();
            fw.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
