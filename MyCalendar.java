public class MyCalendar {
    MyTreeMap<Integer, Integer> calendar;
    
    public MyCalendar() {
        this.calendar = new MyTreeMap<>();
    }
    
    public boolean book(int start, int end) {
        if(start < 0 || start >= end){
            throw new IllegalArgumentException();
        }
        
        if(calendar.ceilingKey(start) == null  && calendar.floorKey(start) == null){        //no events 
            calendar.put(start, end);
            return true;
        }
        else if(calendar.ceilingKey(start) != null || calendar.floorKey(start) != null){    //one event 
            if(calendar.ceilingKey(start) != null){                                         //event after
                int ceilingKey = calendar.ceilingKey(start);
                if(ceilingKey < end){
                    return false;
                }
                else{
                    calendar.put(start, end);
                    return true;
                }
            }
            else{                                                                           //event before
                int floorKey = calendar.floorKey(start);
                if(start < calendar.get(floorKey)){
                    return false;
                }
                else{
                    calendar.put(start, end);
                    return true;
                }
            }
        }
        else{                                                                               //event before & after
            int ceilingKey = calendar.ceilingKey(start);
            int floorKey = calendar.floorKey(start);
            if(ceilingKey < end || start < calendar.get(floorKey)){
                return false;
            }
            else{
                calendar.put(start, end);
                return true;
            }
        }
    }

    public MyTreeMap<Integer, Integer> getCalendar(){
        return this.calendar;
    }
}

/**
 * check if there no events before and after 
 * check if there is an even only before or only after
 *      there is an event before
 *      there is an event after
 * there are nevents before or after
 */