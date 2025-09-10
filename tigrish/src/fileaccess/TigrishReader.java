package fileaccess;

import runtime.*;

import java.io.*;
import java.util.ArrayList;

public class TigrishReader implements TigrishReaderI {
    File file;
    BufferedReader br;
    ArrayList<String> lines;
    int currentLineIndex;
    int curItem;
    ArrayList<String> precheck;
    ArrayList<String> postcheck;

    public TigrishReader() {
        file = null;
        lines = new ArrayList<>();
        curItem = 0;
        currentLineIndex = 0;
        precheck = new ArrayList<>();
        postcheck = new ArrayList<>();
    }
    /**
     * Sets the file to be used by this reader
     * <p>Setting the file can only be done once in the lifetime of the reader. Attempts to
     * change the file after it is set should cause an AlreadySetException to be thrown.</p>
     *
     * @param f
     * @throws FileNotFoundException there is a problem opening the file
     * @throws AlreadySetException   the file has already been set
     */
    @Override
    public void setFile(File f) throws FileNotFoundException, AlreadySetException {
        if (!f.exists()) {
            throw new FileNotFoundException();
        }
        if (file==null) {
            file = f;
            br = new BufferedReader(new FileReader(file));
            try{
                String line;
                while ((line = br.readLine()) != null) {
                    lines.add(line);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }else
            throw new AlreadySetException(f);
    }

    /**
     * Gets the next node from the source file
     *
     * @return the appropriate Node instance or null if there are no more nodes
     * @throws IOException          if there are issues accessing the file
     * @throws SyntaxErrorException if there is a problem parsing the next token from the file
     */
    @Override
    public Node next() throws IOException, SyntaxErrorException {
        if(br == null)
            return null;
        String curline;

        curline= lines.get(currentLineIndex);

        int lineNumber = currentLineIndex + 1;
        boolean canContinue = true;
        if (curline == null){
            return null;
        }
        if (curline.isEmpty()){
            currentLineIndex++;
            canContinue = false;
        }
        if (curline.startsWith("#")){
            currentLineIndex++;
            return new CommentNode(new LineLocation(file,lineNumber), curline);
        }
        curline = curline.trim();
        String[] items = curline.split("\\s+");


        if (curItem >= items.length){
            curItem = 0;
            if(precheck.isEmpty()) {
                currentLineIndex++;
            }
            if (currentLineIndex >= lines.size()) {
                return null;
            }
            return next();
        }

        String item = items[curItem];
        //need to figure out the logic because there are often multiple things on one line

        int i = 0;

        if (i < item.length()&& canContinue) {
            if (!(precheck.isEmpty())) {
                item = precheck.remove(0);
                curItem--;
            }

            if (Character.isDigit(item.charAt(i))) {
                int start = i;
                while (i < item.length()) {
                    if (Character.isDigit(item.charAt(i))) {
                        i++;
                    }else
                        if(postcheck.contains(item)){
                            break;
                        }else{
                            handleNoSpace(item);
                        }
                }
                curItem++;
                return new IntNode(new LineLocation(file, lineNumber), item.substring(start,i));
            } else if (Character.isLetter(item.charAt(i))) {
                int start = i;
                while (i < item.length()) {
                    if (Character.isLetter(item.charAt(i)) || Character.isDigit(item.charAt(i))|| item.charAt(i) == '_')
                        i++;
                    else {
                        if (postcheck.contains(item)) {
                            break;
                        } else {
                            handleNoSpace(item);
                            if (i == item.length()) {
                                return next();
                            }
                        }
                    }
                }
                curItem++;
                return new VarNode(new LineLocation(file, lineNumber), item.substring(start,i));
            } else if ("+-*/=<>".indexOf(item.charAt(i)) != -1) {
                curItem++;
                return new OperatorNode(new LineLocation(file, lineNumber), item.substring(i, i + 1));
            } else if (Character.isWhitespace(item.charAt(i))) {
                i++;
            }
        }
        return null;
    }

    /**
     * Handles no spaces lines
     * @param item the item with no spaces
     */
    public void handleNoSpace(String item) {
        //some logic to figure out what should the thing be if theres a space or
        //add them to items somehow, make items public, then add it there
        //items should be arraylist
        int i=0;
        while (i < item.length()) {
            if ("=*/+-<>".indexOf(item.charAt(i))!=-1) {
                precheck.add(item.substring(i, i+1));
                i++;
            } else if (Character.isDigit(item.charAt(i))) {
                int start = i;
                while (i < item.length()) {
                    if(Character.isDigit(item.charAt(i))){
                        i++;
                    }else
                        break;
                }
                precheck.add(item.substring(start, i));
            } else if (i<item.length()) {
                if(Character.isLetter(item.charAt(i))){}
                    int start = i;
                    while (i < item.length()) {
                        if (Character.isLetter(item.charAt(i)) || Character.isDigit(item.charAt(i))|| item.charAt(i) == '_') {
                            i++;
                        }else
                            break;
                    }
                    precheck.add(item.substring(start, i));
            } else if (Character.isWhitespace(item.charAt(i))) {
                i++;
            } else{
                i++;
            }
        }
        precheck.remove(0);
        postcheck.add(item);

    }
}
