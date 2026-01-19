       program-id. SaveScores.

       environment division.
       configuration section.

       file-control.
       select user-scores assign "userscores.dat"
           access is sequential
           status is file-status.

       data division.
       fd user-scores.
       01 f-score.
         03 f-username   pic x(20).
         03 filler       pic x(2).
         03 f-covered    pic S9(3).

       working-storage section.
       01 file-status  pic 9(2).
       01 score.
         03 username   pic x(20).
         03 filler     pic x(2).
         03 covered    pic S9(3).

       linkage section.
       01 username-in  pic x(20).
       01 covered-in   pic S9(3) COMP-5.

       procedure division using username-in, covered-in.
           move username-in to username
           move covered-in to covered
           move score to f-score

           open extend user-scores
           write f-score after advancing 1 line
           close user-scores

           goback returning 0.

       end program SaveScores.
