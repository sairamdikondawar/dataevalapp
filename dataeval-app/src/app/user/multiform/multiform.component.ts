import {
  ChangeDetectorRef,
  Component,
  EventEmitter,
  Input,
  OnInit,
  Output
} from "@angular/core";
  

import { FormBuilder, FormGroup, Validators } from "@angular/forms";

@Component({
  selector: 'app-multiform',
  templateUrl: './multiform.component.html',
  styleUrls: ['./multiform.component.css']
})
export class MultiformComponent implements OnInit {

  formGroup:FormGroup;

  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  isEditable = false;

  constructor(private fb:FormBuilder) {

  }

  data = [
    {
      'stepname': 'basic',
      'formFields': [
        {
        'key': 'firstName',
        'input': 'text',
        'valids': [{
            'valid': 'required',
            'error': 'firstName is required'
          },
          {
            'valid': 'pattern',
            'validator': '^[a-zA-Z]+$',
            'error': 'firstName is accept only text'
          },
          {
            'valid': 'minlength',
            'length': 3,
            'error': 'firstName must be at least 3 characters'
          }
        ]
        },
        {
          'key': 'middleName',
          'input': 'text',
          'valids': []
        },
        {
          'key': 'lastName',
          'input': 'text',
          'valids': [{
              'valid': 'required',
              'error': 'lastName is required'
            },
            {
              'valid': 'pattern',
              'validator': '^[a-zA-Z]+$',
              'error': 'lastName is accept only text'
            },
            {
              'valid': 'minlength',
              'length': 3,
              'error': 'lastName must be at least 3 characters'
            }
          ]
        },
        {
          'key': 'marital status',
          'input': 'select',
          'items': [{
              'name': 'married',
              'id': 0
            },
            {
              'name': 'unmarried',
              'id': 1
            }
          ],
          'valids': [{}]
        },
        {
          'key': 'gender',
          'input': 'radio',
          'items': [{
              'name': 'male',
              'id': 0
            },
            {
              'name': 'female',
              'id': 1
            }
          ],
          'valids': []
        }
      ]
    },
    {
      'stepname': 'contact',
      'formFields': [
        {
          'key': 'emailId',
          'input': 'email',
          'valids': [{
              'valid': 'required',
              'error': 'emailId is required'
            },
            {
              'valid': 'emailId',
              'error': 'emailId must be valid'
            }
          ]
        },
        {
          'key': 'mobile',
          'input': 'text',
          'valids': [{
              'valid': 'required',
              'error': 'mobile is required'
            },
            {
              'valid': 'pattern',
              'validator': '^[0-9]{10}$',
              'error': 'mobile is accept only number and maximum 10 numbers '
            }
          ]
        },
      ]
    },
    {
      'stepname': 'other',
      'formFields': [
        {
          'key': 'country',
          'input': 'text',
          'valids': [{
              'valid': 'required',
              'error': 'country is required'
            },
          ]
        },
        {
          'key': 'state',
          'input': 'text',
          'valids': [{
              'valid': 'required',
              'error': 'state is required'
            },
          ]
        }
      ]
    }
  ];

  loadFields(){
     
  }

  ngOnInit() {
     
  }

   

}
