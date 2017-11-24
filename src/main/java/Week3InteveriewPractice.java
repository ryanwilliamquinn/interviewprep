import java.util.*;

/**
 * Created by rquinn on 7/15/17.
 */
public class Week3InteveriewPractice {

    // linked lists

    /*
     * find nth node from the end of a linked list
     */
     public ListNode findNthNode(ListNode node, int nthFromEnd) throws Exception {
         int length = 0;
         ListNode currentNode = node;
         while (currentNode != null) {
            currentNode = node.next;
            length++;
         }
         if (nthFromEnd > length) {
             throw new Exception("nth from end is too far");
         }
         int index = length - nthFromEnd - 1;
         currentNode = node;
         while (index > 0) {
            currentNode = node.next;
         }
         return currentNode;
     }

     public ListNode findNthNode2(ListNode node, int nthFromEnd) {
         ListNode leadNode = node;
         ListNode trailingNode = node;
         while (nthFromEnd > 0) {
             leadNode = leadNode.next;
             nthFromEnd--;
         }

         while (leadNode != null) {
             leadNode = leadNode.next;
             trailingNode = trailingNode.next;
         }
         return trailingNode;
     }

     public ListNode findIntersection(ListNode first, ListNode second) {
         Set<ListNode> firstNodes = new HashSet<ListNode>();
         while (first != null) {
             firstNodes.add(first);
             first = first.next;
         }
         while (second != null) {
             if (firstNodes.contains(second)) {
                 return second;
             }
             second = second.next;
         }
         return null;
     }

     public ListNode findIntersection2(ListNode first, ListNode second) {
         while (first != null) {
             while (second != null) {
                 if (first == second) {
                     return first;
                 }
                 second = second.next;
             }
             first = first.next;
         }
         return null;
    }


    Map<String, FBNode> existingUserNodes = new HashMap<>();
    public List<String> findUniqueMachines(Map<String, List<String>> input) {
        // PriorityQueue<FBNode> nodes = new PriorityQueue<>();
        for (Map.Entry<String, List<String>> entry : input.entrySet()) {
            FBNode userNode = new FBNode(entry.getKey());
            // create edges
            for (String serverId : entry.getValue()) {
                FBNode serverNode = null;
                if (existingUserNodes.containsKey(serverId)) {
                    serverNode = existingUserNodes.get(serverId);
                } else {
                    serverNode = new FBNode(serverId);
                }
                serverNode.addInEdge(userNode);
                userNode.addOutEdge(serverNode);
            }
            existingUserNodes.put(userNode.getId(), userNode);
        }
        Queue<FBNode> nodeQueue = new PriorityQueue<>(existingUserNodes.values());


        // nodes are organized by least outgoing edges
        while (nodeQueue.size() > 0) {
            FBNode userNode = nodeQueue.poll();

        }
        return null;
    }
}
