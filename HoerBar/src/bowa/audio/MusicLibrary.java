/**
 * 
 */
package bowa.audio;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Enumeration;
import java.util.Iterator;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * @author Phillip
 *
 */
@SuppressWarnings("serial")
public class MusicLibrary extends DefaultTreeModel implements FileVisitor<Path>{

	protected Path _rootPath = null;

	
	public MusicLibrary(Path root) throws IOException {
		super(new DefaultMutableTreeNode(root));
		_rootPath = root;
		
		addToLib(_rootPath);	
	}

	
	public void addToLib(Path dir) throws IOException{
		Files.walkFileTree(dir, this);
	}
	

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
			throws IOException {


		
		if(attrs.isRegularFile()){
			if(file.getFileName().toString().toLowerCase().endsWith("mp3")){
				
				Path p = _rootPath.relativize(file);
				
				DefaultMutableTreeNode parent = (DefaultMutableTreeNode) getRoot();
				
				Iterator<Path> iter = p.iterator();
				while(iter.hasNext()){
					parent = appendNode(parent, new DefaultMutableTreeNode(iter.next()));				
				}
				
			}
		}
		
		return FileVisitResult.CONTINUE;
	}

	
	protected DefaultMutableTreeNode appendNode(DefaultMutableTreeNode parent, DefaultMutableTreeNode child){
		
		@SuppressWarnings("rawtypes")
		Enumeration e = parent.children();
		DefaultMutableTreeNode current = null;
		
		while(e.hasMoreElements()){
			current = (DefaultMutableTreeNode)e.nextElement();
			if(current.getUserObject().equals(child.getUserObject())){
				return current;
			}
		}
		parent.add(child);
		return child;
	}
	
	
	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc)
			throws IOException {
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
			throws IOException {
		return FileVisitResult.CONTINUE;
	}
	
	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc)
			throws IOException {
		return FileVisitResult.SKIP_SUBTREE;
	}


}
