/**
 * Created by Joe on 11/20/2014.
 */

public class ArrayNode {

    ArrayNode prevNode = null,
              nextNode = null;
    int index=0;

    Object object = null;

    ArrayNode(){
        index=-1;
    }//..

    ArrayNode(Object object, int index,ArrayNode prevNode){
        this.object = object;
        this.index=index;
        this.prevNode=prevNode;
    }

    public void add(Object object){
        if (index==-1){
            this.object=object;
            index=0;
        }else if(nextNode == null){
            //System.out.println("adding new olymp");
            nextNode = new ArrayNode(object,index+1,this);
        }else {
            nextNode.add(object);
        }
    }//..

    public int size(){
        if(nextNode==null){
            return 1;
        }else {
            return 1 + nextNode.size();
        }
    }//..

    public Object get(int index){
      if(this.index == index){
          return object;
      }else if (this.index < index && nextNode!=null){
            return nextNode.get(index);
      }else if(prevNode != null){
          return prevNode.get(index);
      }
        return null;
    }//..

}//.. end Class ArrayNode
